DESCRIPTION = "Generate pack.img"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0ceb3372c9595f0a8067e55da801e4a1"

DEPENDS = "emutils-native virtual/kernel"

PV = "1.0"

inherit deploy

PACK_IMG = "pack-${MACHINE}-${PV}-${PR}.img"
PACK_IMG_SYMLINK = "pack-${MACHINE}.img"
PACK_IMG_SYMLINK_SIMPLE = "pack.img"

do_compile() {
	packimg -p 2048 ${DEPLOY_DIR_IMAGE}/uImage-${MACHINE}.dtb@44000000 ${DEPLOY_DIR_IMAGE}/fex-${MACHINE}.bin@43000000 ${B}/${PACK_IMG}
}

do_deploy() {
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

