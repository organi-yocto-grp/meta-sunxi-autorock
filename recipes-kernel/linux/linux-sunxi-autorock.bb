require recipes-kernel/linux/linux-dtb.inc

DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo

SUMMARY = "Linux kernel for Autorock sunxi boards"

SRC_URI = "git://git@git.autorock.com/sunxi/linux.git;branch=${SRCBRANCH};protocol=ssh \
           file://defconfig"

SRCBRANCH = "master"
SRCREV = "${AUTOREV}"
LOCALVERSION = "-git${SRCREV}"

DEPENDS += "lz4-native bc-native u-boot-mkimage-native"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "a20navi"

PV = "3.4+git${SRCPV}"

#fix QA issue "Files/directories were installed but not shipped: /usr/src/debug"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure_prepend() {
	#fix arch QA issues ("Architecture did not match")
	rm -f ${S}/sunxi-board/script
	rm -f ${S}/sunxi-board/fex2bin
	rm -f ${S}/drivers/net/wireless/rtxx7x/tools/bin2h
	rm -f ${S}/modules/wifi/ar6302/AR6K_SDK_ISC.build_3.1_RC.329/host/lib/wac/wac
	rm -f ${S}/modules/wifi/ar6302/AR6K_SDK_ISC.build_3.1_RC.329/host/tools/pal_host_intf/pal_app
	rm -f ${S}/modules/wifi/nano-c047.12/obj/hic-proxy
	rm -f ${S}/modules/wifi/nano-c047.12/obj/x_mac_4.69.axf
	rm -f ${S}/modules/wifi/nano-c047.12/obj/x_mac_patch_4_65.axf
	rm -f ${S}/modules/wifi/nano-c047.12/obj/x_mac_4.66.axf
	rm -f ${S}/modules/wifi/nano-c047.12/obj/x_mac-v4.68.axf
	rm -f ${S}/modules/wifi/nano-c047.12/obj/x_mac.axf
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/wl
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/socket_noasd/x86/wl_server_socket
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/wifi_noasd/x86/wl_server_serial
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/wifi_noasd/x86/wl_server_wifi
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/make/wl
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/make/socket_noasd/x86/wl_server_socket
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/make/wifi_noasd/x86/wl_server_serial
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/make/wifi_noasd/x86/wl_server_wifi
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/make/dongle_noasd/x86/wl_server_dongle
	rm -f ${S}/modules/wifi/bcm40181/open-src/src/wl/exe/dongle_noasd/x86/wl_server_dongle
	rm -f ${S}/modules/wifi/bcm40181/apps/tc_cli
	rm -f ${S}/modules/wifi/bcm40181/apps/wfa_ca
	rm -f ${S}/modules/wifi/bcm40181/apps/dhd
	rm -f ${S}/modules/wifi/bcm40181/apps/ca_cli

	#fix ldflags QA issues ("No GNU_HASH in the elf binary")
	rm -f ${S}/modules/wifi/usi-bcm4329/v4.218.248.15/apps/epi_ttcp
	rm -f ${S}/modules/wifi/bcm40181/apps/epi_ttcp
}

#KERNEL_MODULE_AUTOLOAD += "mali"