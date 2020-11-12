# Atari TOS/GEM Image Reader

This is a small Java library with ImageIO plugins (readers) for some really old Atari TOS/GEM image
file formats. And of course it is a fun project to preserve my very first (IIRC) two
[Java applets](https://snailshell.de/java.html) from 1996, buried somewhere in the
[Git history](https://github.com/thmuch/tosgem-image-reader/tree/1996-08) :-)


## Supported image formats

- Monochrome GEM images (file extension \*.IMG)
- Monochrome GEM 32000 bytes 640x400 images (file extension \*.PIC or \*.DOO).

## TODO

- Import b/w PSC images. **P**aint **S**hop **C**ompressed was the native image format of my TOS
  application "PaintShop" that I wrote around 1991.
- The algorithms in `GEMImage` and `GEMPicture` certainly need some refactoring. But for the time being,
  I kept the structures close to their originals from 1996.
- Reader for STAD PAC images
- GitHub Actions pipeline & deployment to Maven Central

## Credits

- "tiger.img" apparently belongs to the public domain, so thank you whoever created that picture!
- "poster.pic" was drawn by my cousin Sebastian Mayer with an early version of my TOS application "PaintShop"
  (and exported as a GEM PIC file afterwards).

!["That's it"](src/test/resources/images/expected/poster.png)
