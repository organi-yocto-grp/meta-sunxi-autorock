SUMMARY = "dashboard update application"
SECTION = "autorock"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@git.autorock.com/njgdbus-dashboard/update.git;branch=${SRCBRANCH};protocol=ssh \
           file://update \
"

SRCBRANCH = "master"
SRCREV = "a3b6017d647dac77bccdd650f0c1ca0d3cc7354c"
PV = "1.1+git${SRCPV}"

S = "${WORKDIR}/git"

require recipes-qt/qt5/qt5.inc

DEPENDS = "qtdeclarative qtquickcontrols"
DEPENDS += "update-rc.d-native"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/bin/A20Update ${D}${bindir}

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/update ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} update start 45 S .
}

RDEPENDS_${PN} = "qtbase-plugins qtdeclarative-qmlplugins qtquickcontrols-qmlplugins"
