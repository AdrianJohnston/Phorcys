import gevent

from flask import Flask, Response
from flask import render_template
from flask import request

import json
from gevent.wsgi import WSGIServer
from gevent.queue import Queue
import time


app = Flask(__name__)
subscriptions = []


# @app.route('/')
# def index():
#     return render_template("index.html")

@app.route('/health/', methods=['GET'])
def health():
    return '200 OK'

debug_template = """
     <html>
       <head>
       </head>
       <body>
         <h1>Server sent events</h1>
         <div id="event"></div>
         <script type="text/javascript">

         var eventOutputContainer = document.getElementById("event");
         var evtSrc = new EventSource("/events");

         evtSrc.onmessage = function(e) {
             console.log(e.data);
             eventOutputContainer.innerHTML = e.data;
         };

         </script>
       </body>
     </html>
    """

class ServerSentEvent(object):

    def __init__(self, data):
        self.data = data
        self.event = None
        self.id = None
        self.desc_map = {
            self.data : "data",
            self.event : "event",
            self.id : "id"
        }

    def encode(self):
        if not self.data:
            return ""
        lines = ["%s: %s" % (v, k)
                 for k, v in self.desc_map.iteritems() if k]

        return "%s\n\n" % "\n".join(lines)

# Client code consumes like this.
@app.route("/")
def index():
    return render_template("index.html")

@app.route("/events", methods=['GET', 'POST'])
def events():
    req = request
    if req.method == 'POST':
        print("POST OK")
        return publish()
    elif req.method == 'GET':
        print("GET OK")
        print(subscriptions)
        return subscribe()

def publish():
    print("Publishing")

    payload = request.data
    print("Error in payload")
    try:
        print ("Data", payload)
        data = json.loads(request.data)
    except:

        return str({'error':'invalid payload'})

    def notify():
        for sub in subscriptions[:]:
            sub.put(payload)

    gevent.spawn(notify)
    return "OK"


def subscribe():

    print("Subscribing")
    def gen():
        q = Queue()
        subscriptions.append(q)
        try:
            while True:
                result = q.get()
                ev = ServerSentEvent(str(result))
                yield ev.encode()
        except GeneratorExit: # Or maybe use flask signals
            subscriptions.remove(q)

    return Response(gen(), mimetype="text/event-stream")



if __name__ == '__main__':

    app.debug = True
    server = WSGIServer(("", 5000), app)
    server.serve_forever()