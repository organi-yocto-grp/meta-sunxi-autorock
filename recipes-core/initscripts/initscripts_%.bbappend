FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://gpio \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/gpio ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} gpio start 40 S .
}
