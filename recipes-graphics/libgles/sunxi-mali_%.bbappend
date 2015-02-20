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

# fix other package can't find runtime dependent of this package
# due to libMali.so has no SONAME field
python package_do_shlibs_append() {
    pn = d.getVar('PN', True)
    libdir = d.getVar('libdir', True)
    shlibs_file = os.path.join(shlibswork_dir, pn + ".list")
    fd = open(shlibs_file, 'w')
    fd.write('libEGL.so:' + libdir + ':' + ver + '\n')
    fd.write('libGLESv1_CM.so:' + libdir + ':' + ver + '\n')
    fd.write('libGLESv2.so:' + libdir + ':' + ver + '\n')
    fd.close()
}

FILES_${PN}-dev += "${libdir}/pkgconfig"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
