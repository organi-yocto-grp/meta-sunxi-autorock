DESCRIPTION = "Allwinner A10 and A20 fexc tools"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRCBRANCH = "master"

SRC_URI = "git://git@git.autorock.com/sunxi/sunxi-tools.git;branch=${SRCBRANCH};protocol=ssh"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"

FILES_${PN} = "${bindir}/*"

CFLAGS = "-std=c99 -D_POSIX_C_SOURCE=200112L -I./include"
CFLAGS_class-native = "-std=c99 -D_POSIX_C_SOURCE=200112L -I./include"

do_compile() {
	oe_runmake fexc
}

do_install() {
	install -d ${D}/${bindir}
	install -m 755 ${S}/fexc ${D}/${bindir}
	ln -snf fexc ${D}/${bindir}/bin2fex
	ln -snf fexc ${D}/${bindir}/fex2bin
}
