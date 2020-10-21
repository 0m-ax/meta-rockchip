# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Tools and firmwares for rockchip npu"
SECTION = "utils"

LICENSE = "LICENSE.rockchip"
LIC_FILES_CHKSUM = "file://${RK_BINARY_LICENSE};md5=5fd70190c5ed39734baceada8ecced26"

RDEPENDS_${PN} = "bash"

PATCHPATH = "${THISDIR}/files"

SRC_URI = " \
	git://github.com/JeffyCN/mirrors.git;branch=rknpu-fw; \
	file://rockchip-npu.sh \
"
SRCREV = "23d2a0b92d5484b8d6a0618e7b32aa39e7c171f2"
S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${datadir}/npu_fw
	install -m 0644 npu_fw/* ${D}${datadir}/npu_fw/

	install -d ${D}${datadir}/npu_fw_pcie
	install -m 0644 npu_fw_pcie/* ${D}${datadir}/npu_fw_pcie/

	install -d ${D}${bindir}
	# FIXME: support different arch
	install -m 0755 bin/* ${D}${bindir}

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/rockchip-npu.sh ${D}${sysconfdir}/init.d/
}

inherit update-rc.d

INITSCRIPT_NAME = "rockchip-npu.sh"
INITSCRIPT_PARAMS = "start 11 S ."

INSANE_SKIP_${PN} = "already-stripped ldflags"

FILES_${PN} = " \
	${datadir} \
	${bindir} \
	${sysconfdir}/init.d \
"
