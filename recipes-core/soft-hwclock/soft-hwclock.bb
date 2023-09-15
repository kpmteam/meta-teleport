DESCRIPTION = "Installs the Fake Hardware Clock daemon"
HOMEPAGE = "https://github.com/kristjanvalur/soft-hwclock"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/kristjanvalur/soft-hwclock.git;protocol=https;branch=master \
file://0001-soft-hwclock.patch \
"
SRCREV = "aeaea431fb9d17005fefeeb34b80ea6fcc76adde"

S = "${WORKDIR}/git"

INSTALL_DIR = "/opt/soft-hwclock"

inherit systemd

SYSTEMD_SERVICE:${PN} = "soft-hwclock.service soft-hwclock-tick.service soft-hwclock-tick.timer"

do_install() {
    install -d ${D}${INSTALL_DIR}
    install -m 0755 ${S}/soft-hwclock ${D}${INSTALL_DIR}
    
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/soft-hwclock.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/soft-hwclock-tick.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/soft-hwclock-tick.timer ${D}${systemd_system_unitdir}


}

FILES:${PN} = "${INSTALL_DIR} \
                ${INSTALL_DIR}/soft-hwclock \
                ${systemd_system_unitdir}/soft-hwclock.service \
                ${systemd_system_unitdir}/soft-hwclock-tick.service \
                ${systemd_system_unitdir}/soft-hwclock-tick.timer \
                "

