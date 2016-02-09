# PyDisplay: a browser-based graphics server
Originally forked from [display](https://github.com/szym/display) which was forked from [gfx.js](https://github.com/clementfarabet/gfx.js/).

The ultimate goal of this project is to provide a simple visualisation pacakge for theano based deep learning projects.

Install for Python (`numpy` required) via:

    python setup.py install [--user]



See `example.py` for sample usage.

    disp = require 'display'
    disp.image(image.lena())
    disp.plot(torch.cat(torch.linspace(0, 10, 10), torch.randn(10), 2))

### Supported commands

Common parameters:
  - `win`: identifier of the window to be reused (pick a random one if you want a new window)
  - `title`: title for the window title bar

`image` creates a zoomable `<img>` element
  - `src`: URL for the `<img>` element
  - `width`: initial width in pixels
  - `labels`: array of 3-element arrays `[ x, y, text ]`, where `x`, `y` are the coordinates
    `(0, 0)` is top-left, `(1, 1)` is bottom-right; `text` is the label content

`plot` creates a Dygraph, all [Dygraph options](http://dygraphs.com/options.html) are supported
  - `file`: see [Dygraph data formats](http://dygraphs.com/data.html) for supported formats
  - `labels`: list of strings, first element is the X label

`text` sends raw HTML

## Technical overview

The server is a trivial message forwarder:

    POST /events -> EventSource('/events')

The Lua client sends JSON commands directly to the server. The browser script
interprets these commands, e.g.

    { command: 'image', src: 'data:image/png;base64,....', title: 'lena' }
