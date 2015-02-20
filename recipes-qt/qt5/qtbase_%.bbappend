FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://add-sunxi-eglfs.patch"
PACKAGECONFIG_GL = "gles2"

do_configure_prepend() {
	# adapt qmake.conf to our needs
	sed -i 's!load(qt_config)!!' ${S}/mkspecs/linux-oe-g++/qmake.conf

	# copy the hook in the mkspecs directory OE is using
	cp ${S}/mkspecs/devices/linux-sunxi-g++/qeglfshooks_sunxi.cpp ${S}/mkspecs/linux-oe-g++/

	cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF
EGLFS_PLATFORM_HOOKS_SOURCES = \$\$PWD/qeglfshooks_sunxi.cpp

QMAKE_LIBS_EGL         += -lEGL
QMAKE_LIBS_OPENGL_ES2  += -lGLESv2 -lEGL

load(qt_config)

EOF
}

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

