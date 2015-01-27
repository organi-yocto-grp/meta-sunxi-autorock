require conf/machine/include/tune-cortexa7.inc
require conf/machine/include/sunxi.inc
require conf/machine/include/sunxi-mali.inc

DEFAULTTUNE = "cortexa7hf-neon-vfpv4"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_virtual/kernel = "linux-sunxi-autorock"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"

IMAGE_FSTYPES = "tar.bz2"

MACHINE_FEATURES_remove = "usbgadget usbhost alsa touchscreen"

# remove getty tty* from /etc/inittab
USE_VT = "0"