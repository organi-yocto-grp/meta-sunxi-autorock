DESCRIPTION = "EMUTILS"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0ceb3372c9595f0a8067e55da801e4a1"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRCBRANCH = "master"

SRC_URI = "git://git@git.autorock.com/sunxi/utils.git;branch=${SRCBRANCH};protocol=ssh"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"

do_compile() {
	oe_runmake -C emconfig
#	oe_runmake -C monserver
	oe_runmake -C packimg
	oe_runmake -C mksplash
}

do_install() {
	install -d ${D}/${bindir}
	install -d ${D}/${sbindir}
	install -m 755 ${S}/emconfig/emconfig ${D}/${sbindir}
	install -m 755 ${S}/mksplash/mksplash ${D}/${sbindir}
#	install -m 755 ${S}/monserver/monserver ${D}/${sbindir}
#	ln -snf monserver ${D}/${sbindir}/enumd
	install -m 755 ${S}/packimg/packimg ${D}/${bindir}
	ln -snf packimg ${D}/${bindir}/packimg_burn
	ln -snf packimg ${D}/${bindir}/unpackimg
}

# prevent already-stripped QA Issue
INHIBIT_PACKAGE_STRIP = "1"

PACKAGES = "emconfig mksplash packimg"
#PACKAGES += "monserver"

FILES_emconfig = "${sbindir}/emconfig"
FILES_mksplash = "${sbindir}/mksplash"
#FILES_monserver = "${sbindir}/monserver ${sbindir}/enumd"
FILES_packimg = "${bindir}/packimg ${bindir}/packimg_burn ${bindir}/unpackimg"

