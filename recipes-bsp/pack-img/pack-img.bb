DESCRIPTION = "Generate pack.img"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "emutils-native virtual/kernel sunxi-board-fex-autorock"

PV = "1.0"

SRC_URI = "file://logo.bmp"

inherit deploy

SPLASH_BIN = "splash-${MACHINE}-${PV}-${PR}-${DATETIME}.bin"
SPLASH_BIN_SYMLINK = "splash-${MACHINE}.bin"
SPLASH_BIN_SYMLINK_SIMPLE = "splash.bin"

PACK_IMG = "pack-${MACHINE}-${PV}-${PR}-${DATETIME}.img"
PACK_IMG_SYMLINK = "pack-${MACHINE}.img"
PACK_IMG_SYMLINK_SIMPLE = "pack.img"

do_compile() {
	mksplash -b32 ${WORKDIR}/logo.bmp -o ${B}/${SPLASH_BIN}
	packimg -p 2048 ${DEPLOY_DIR_IMAGE}/uImage-${MACHINE}.dtb@44000000 ${DEPLOY_DIR_IMAGE}/fex-${MACHINE}.bin@43000000 ${B}/${SPLASH_BIN}@43100000 ${B}/${PACK_IMG}
}

do_deploy() {
	install -m 0644 ${B}/${SPLASH_BIN} ${DEPLOYDIR}/
    ln -snf ${SPLASH_BIN} ${DEPLOYDIR}/${SPLASH_BIN_SYMLINK}
    ln -snf ${SPLASH_BIN} ${DEPLOYDIR}/${SPLASH_BIN_SYMLINK_SIMPLE}

    install -m 0644 ${B}/${PACK_IMG} ${DEPLOYDIR}/
    ln -snf ${PACK_IMG} ${DEPLOYDIR}/${PACK_IMG_SYMLINK}
    ln -snf ${PACK_IMG} ${DEPLOYDIR}/${PACK_IMG_SYMLINK_SIMPLE}
}
addtask deploy before do_build after do_compile

PACKAGES = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_populate_sysroot[noexec] = "1"

