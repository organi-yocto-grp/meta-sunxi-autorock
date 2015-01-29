require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for Autorock sunxi boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"
COMPATIBLE_MACHINE = "a20navi"

PROVIDES = "u-boot"

SRCREV = "${AUTOREV}"
SRCBRANCH = "master"
SRC_URI = "git://git@git.autorock.com/sunxi/uboot.git;branch=${SRCBRANCH};protocol=ssh"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "git${SRCPV}"

EXTRA_OEMAKE = 'CROSS_COMPILE=/opt/arm-2010q1/bin/arm-none-linux-gnueabi-'
