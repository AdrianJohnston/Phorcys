from flask import Flask
from flask import render_template
from flask import request
import json


app = Flask(__name__)


@app.route('/')
def index():
    return render_template("index.html")

@app.route('/events', methods=['GET', 'POST'])
def event():
    ##Have to use server sent events
    print("Reciveving Request")
    req = request

    if req.method == 'POST':
        print("Method = POST")
        data = json.loads(request.data)
        print()
        # def notify():
        #     msg = build_post_response(body=data)
        #     for sub in subscriptions:
        #         sub.put(msg)
        #
        # gevent.spawn(notify)
        # return None
    elif req.method == 'GET':
        print("METHOD = GET")
        # def gen():
        #     q = Queue()
        #     subscriptions.append(q)
        #     try:
        #         while True:
        #             result = q.get()
        #             ev = ServerSentEvent(str(result))
        #             yield ev.encode()
        #     except GeneratorExit: # Or maybe use flask signals
        #         subscriptions.remove(q)

        # return Response(gen(), mimetype="text/event-stream")

    return render_template("index.html")

# def build_post_response(body):
#
#     headers = {
#       'Content-Type' : 'text/event-stream',
#       'Cache-Control' : 'no-cache',
#       'Connection'    : 'keep-alive',
#       'Transfer-Encoding' : 'chunked'
#     }
#
#     body = req.body
#     message = 'data: ' + str(body) + '\n\n\r\n'
#     return message

if __name__ == '__main__':
    app.run()