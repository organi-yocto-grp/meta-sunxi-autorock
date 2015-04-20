DESCRIPTION = "Generate pack.img"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pack_img

DEPENDS += "virtual/kernel splash-pack sunxi-board-fex-autorock"

PV = "1.0"

do_compile() {
	packimg -p 2048 ${DEPLOY_DIR_IMAGE}/uImage-${KERNEL_DEVICETREE}@44000000 ${DEPLOY_DIR_IMAGE}/fex-${MACHINE}.bin@43000000 ${DEPLOY_DIR_IMAGE}/splash.bin@43100000 ${B}/${PACK_IMG}
}
