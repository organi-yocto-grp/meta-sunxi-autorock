require recipes-kernel/linux/linux-dtb.inc

DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo

SUMMARY = "Linux kernel for Autorock sunxi boards"

SRC_URI = "git://git@git.autorock.com/sunxi/linux.git;branch=${SRCBRANCH};protocol=ssh \
           file://defconfig"

SRCBRANCH = "master"
SRCREV = "${AUTOREV}"
LOCALVERSION = "-git${SRCREV}"

DEPENDS += "lz4-native bc-native u-boot-mkimage-native"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "a20navi"

PV = "3.4+git${SRCPV}"

#fix QA issue "Files/directories were installed but not shipped: /usr/src/debug"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure_prepend() {
    #fix arch QA issues ("Architecture did not match")
	rm ${S}/sunxi-board/script
	rm ${S}/sunxi-board/fex2bin
}

