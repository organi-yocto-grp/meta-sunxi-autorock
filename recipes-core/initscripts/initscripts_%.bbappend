FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://gpio \
	file://ubifs \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/gpio ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} gpio start 40 S .

	install -m 0755 ${WORKDIR}/ubifs ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} ubifs start 02 S .
}
