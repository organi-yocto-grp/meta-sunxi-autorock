SUMMARY = "dashboard update application"
SECTION = "autorock"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@git.autorock.com/njgdbus-dashboard/update.git;branch=${SRCBRANCH};protocol=ssh \
           file://update \
"

SRCBRANCH = "master"
SRCREV = "00104ec7cc67bbb24c3172f64bacb3463b3c72d0"
PV = "1.0+git${SRCPV}"

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
