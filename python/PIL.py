# The Python Imaging Library (PIL)

import PIL
from PIL import Image

Image.open

#    This is a lazy operation; this function identifies the file, but
#    the file remains open and the actual image data is not read from
#    the file until you try to process the data (or call the
#    :py:meth:`~PIL.Image.Image.load` method)

file="readonly/some_file.gif"
image=Image.open(file)
print(image)

#    <PIL.GifImagePlugin.GifImageFile image mode=P size=800x450 at 0x7F4480A544E0>

