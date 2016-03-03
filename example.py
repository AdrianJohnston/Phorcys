from __future__ import print_function
import numpy
import random
from Phorcys import *


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


txt_win = text("Hello World", title='Text Pane Test')

mesh("This will be mesh data", title='Mesh Pane Test')

##Generate 3D surface

import numpy as np

steps = 50  # number of datapoints will be steps*steps
axisMax = 314

def generate_3D_grid(steps, axisMax):

  axisStep = float(axisMax / steps)
  X = np.arange(0, axisMax, axisStep)
  Y = np.arange(0, axisMax, axisStep)
  X, Y = np.meshgrid(X, Y)
  Z = np.sin(X/50) * np.cos(Y/5) * 5 + 500
  X = X.flatten()
  Y = Y.flatten()
  Z = Z.flatten()
  data = np.concatenate([X[...,np.newaxis],Y[...,np.newaxis],Z[...,np.newaxis]], axis=1)
  return data

data = generate_3D_grid(steps, axisMax)

print (data.shape)

win2 = graph3d(data, labels=[ 'position', 'a', 'b' ], title='progress')
