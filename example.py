from __future__ import print_function
import numpy
import random
from PyDisplay import *


def generate_image():
  X, Y = numpy.meshgrid(numpy.linspace(0, numpy.pi, 512), numpy.linspace(0, 2, 512))
  z = (numpy.sin(X) + numpy.cos(Y)) ** 2 + 0.5
  return z

i1 = generate_image()
i2 = generate_image()

print("Generated Images")

image(i1, title='gradient')

print("Displaying Images")

# display.images([i2, i2, i2, i2], width=200, title='super fabio', labels=['a', 'b', 'c', 'd'])

data = []
for i in range(15):
  data.append([i, random.random(), random.random() * 2])

win = plot(data, labels=[ 'position', 'a', 'b' ], title='progress')

for i in range(15, 25):
  time.sleep(0.2)
  data.append([i, random.random(), random.random() * 2])
  plot(data, win=win)
