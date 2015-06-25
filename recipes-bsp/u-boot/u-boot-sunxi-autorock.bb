require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for Autorock sunxi boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"
COMPATIBLE_MACHINE = "a20navi"

PROVIDES = "u-boot"

SRCREV = "3b2b2743a7aa3d3c3b4bfcb68351957a5cf15c77"
SRCBRANCH = "master"
SRC_URI = "git://git@git.autorock.com/sunxi/uboot.git;branch=${SRCBRANCH};protocol=ssh"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "v2014.04+git${SRCPV}"
PR = "r0"

SEPB = "${WORKDIR}/build"
B = "${SEPB}"

EXTRA_OEMAKE = 'CROSS_COMPILE=/opt/arm-2012.09/bin/arm-none-linux-gnueabi-'

do_configure() {
    make ${EXTRA_OEMAKE} ${UBOOT_MACHINE} -C ${S} O=${B}
}

do_compile_append() {
    cp ${B}/${SPL_BINARY} ${S}/${SPL_BINARY}
    cp ${B}/${UBOOT_BINARY} ${S}/${UBOOT_BINARY}
}
