FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://egl.pc file://glesv2.pc"

COMPATIBLE_MACHINE = "a20navi"
DEPENDS = "libump"
PACKAGECONFIG = "framebuffer"
PACKAGECONFIG[framebuffer] = "EGL_TYPE=framebuffer,,,"

do_install_append() {
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${WORKDIR}/egl.pc ${D}${libdir}/pkgconfig/egl.pc
	install -m 0644 ${WORKDIR}/glesv2.pc ${D}${libdir}/pkgconfig/glesv2.pc
}

FILES_${PN}-dev += "${libdir}/pkgconfig"
