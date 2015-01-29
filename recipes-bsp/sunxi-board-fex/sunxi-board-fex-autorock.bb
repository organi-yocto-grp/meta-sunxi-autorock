DESCRIPTION = "Handler for Allwinner's FEX files"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0ceb3372c9595f0a8067e55da801e4a1"

DEPENDS = "sunxi-fexc-native"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRCBRANCH = "master"

SRC_URI = "git://git@git.autorock.com/sunxi/sunxi-board.git;branch=${SRCBRANCH};protocol=ssh"

S = "${WORKDIR}/git"
SUNXI_FEX_FILE_a20navi = "a20-navi.fex"

SUNXI_FEX_BIN_IMAGE = "fex-${MACHINE}-${PV}-${PR}.bin"
SUNXI_FEX_BIN_IMAGE_SYMLINK = "fex-${MACHINE}.bin"
SUNXI_FEX_BIN_IMAGE_SYMLINK_SIMPLE = "fex.bin"

inherit deploy

do_compile() {
    fex2bin "${S}/${SUNXI_FEX_FILE}" > "${B}/${SUNXI_FEX_BIN_IMAGE}"
}

do_deploy() {
    install -m 0644 ${B}/${SUNXI_FEX_BIN_IMAGE} ${DEPLOYDIR}/
    ln -snf ${SUNXI_FEX_BIN_IMAGE} ${DEPLOYDIR}/${SUNXI_FEX_BIN_IMAGE_SYMLINK}
    ln -snf ${SUNXI_FEX_BIN_IMAGE} ${DEPLOYDIR}/${SUNXI_FEX_BIN_IMAGE_SYMLINK_SIMPLE}
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

COMPATIBLE_MACHINE = "(a20navi)"

