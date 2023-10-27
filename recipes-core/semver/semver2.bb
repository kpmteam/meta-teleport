DESCRIPTION = "SH script to compare versions using semversion 2.0 spec. highly-cross-compatible shell compliant with the POSIX standard. Semversion 2.0 compliant: https://semver.org/"
HOMEPAGE = "https://github.com/Ariel-Rodriguez/sh-semversion-2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/Ariel-Rodriguez/sh-semversion-2;protocol=https;branch=main;"
SRCREV = "afbcaa37f85cd944db9b29e41549d95e4322dc10"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/semver2.sh ${D}${bindir}
}

FILES:${PN} = "${bindir}/semver2.sh"
