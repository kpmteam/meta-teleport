DESCRIPTION = "Installs the Teleport Client"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILE_NAME = "teleport-v${PV}-linux-${TARGET_ARCH}-bin.tar.gz"

SRC_URI = "https://get.gravitational.com/${FILE_NAME}"

SHASUM_URI = "https://get.gravitational.com/${FILE_NAME}.sha256"

# Disable already-stripped as it is not needed
INSANE_SKIP:${PN} += "already-stripped"

# get the sha256sum from the remote file
do_fetch[prefuncs] += "fetch_checksums"
python fetch_checksums() {
    import urllib
    for line in urllib.request.urlopen(d.getVar("SHASUM_URI")):
        (sha, filename) = line.decode("ascii").strip().split()
        if filename == d.getVar("FILE_NAME"):
            d.setVarFlag("SRC_URI", "sha256sum", sha)
            return
    bb.error("Could not find remote checksum")
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/teleport/teleport ${D}${bindir}/teleport
    install -m 0755 ${WORKDIR}/teleport/tctl ${D}${bindir}/tctl
    install -m 0755 ${WORKDIR}/teleport/tsh ${D}${bindir}/tsh
    install -m 0755 ${WORKDIR}/teleport/tbot ${D}${bindir}/tbot

    install -d ${D}/var/lib/teleport
}

FILES_${PN} = "${bindir}/teleport \
${bindir}/tctl \
${bindir}/tsh \
${bindir}/tbot \
"
