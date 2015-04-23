require conf/machine/include/tune-cortexa7.inc
require conf/machine/include/sunxi.inc
require conf/machine/include/sunxi-mali.inc

DEFAULTTUNE = "cortexa7hf-neon-vfpv4"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_u-boot = "u-boot-sunxi-autorock"
PREFERRED_PROVIDER_virtual/kernel = "linux-sunxi-autorock"
PREFERRED_VERSION_linux-libc-headers = ""

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"

IMAGE_FSTYPES = "tar.bz2"

MACHINE_FEATURES_remove = "usbgadget alsa touchscreen"

# remove getty tty* from /etc/inittab
USE_VT = "0"

SPL_BINARY = "sunxi-spl.bin"
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = "all"

MACHINE_SOCARCH = "${TUNE_PKGARCH}-sunxi"
PACKAGE_EXTRA_ARCHS_append = " ${MACHINE_SOCARCH}"

UBOOT_ENTRYPOINT = "0x48000000"

# K9F4G08U0A
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 2048 -x zlib"

KERNEL_DEVICETREE = "a20navi-njgdbus.dtb"

UBOOT_CONFIG ??= "nand"
UBOOT_CONFIG[sd] = "EP1000A_MMC_NODATA_config"
UBOOT_CONFIG[nand] = "EP1000A_NODATA_config"

MACHINE_FEATURES_remove += "rtc"

KERNEL_MODULE_AUTOLOAD += "ntfs"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
kernel-module-ntfs \
"

MACHINEOVERRIDES = "a20navi:${MACHINE}"
MACHINE_CLASS = "a20navi"