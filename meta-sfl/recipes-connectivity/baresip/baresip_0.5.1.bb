SUMMARY = "baresip"
DESCRIPTION = "Baresip is a portable and modular SIP User-Agent with audio and video support."
HOMEPAGE = "http://creytiv.com/baresip.html"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://docs/COPYING;md5=b429dd27e797136d56af4dfd3ca4e9be"
DEPENDS = "openssl libre librem alsa-lib"
RDEPENDS_${PN} = "libre librem"

SRC_URI = "https://github.com/alfredh/baresip/archive/v${PV}.tar.gz;downloadfilename=${PN}_${PV}.tar.gz"

SRC_URI[md5sum] = "529f95a091965cd7d09172637ec8be62"
SRC_URI[sha256sum] = "71c7d27b81221bc653908483a543626bd59a3245fef0b67546ba4f0cbe5aaae8"

EXTRA_OEMAKE = "\
    'LIBRE_MK=${STAGING_DATADIR}/re/re.mk' \
    'LIBRE_INC=${STAGING_INCDIR}/re' \
    'LIBRE_SO={STAGING_LIBDIR}' \
    'EXTRA_CFLAGS=${CFLAGS}' \
    'LD=${CC}' \
    'EXTRA_LFLAGS=${TARGET_LDFLAGS}' \
    'OS=linux' \
    'ARCH=${TARGET_ARCH}' \
    'SYSROOT=${STAGING_EXECPREFIXDIR}' \
    'DESTDIR=${D}' \
"

do_compile() {
    unset CFLAGS
    unset CXXFLAGS
    oe_runmake all
}

do_install() {
    oe_runmake install
}

FILES_${PN}-dbg += "${libdir}/baresip/modules/.debug/*"
