SUMMARY = "libre"
DESCRIPTION = "Generic library for real-time communications with async IO support."
HOMEPAGE = "http://creytiv.com/re.html"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://docs/COPYING;md5=355a65b02e6130ac384129d16c0598df"
DEPENDS = "openssl zlib"

SRC_URI = "https://github.com/creytiv/re/archive/v${PV}.tar.gz;downloadfilename=${PN}_${PV}.tar.gz"

SRC_URI[md5sum] = "69d12dd2a9f9d56a5a8ea9274955605d"
SRC_URI[sha256sum] = "3b24654ed6ab907487dd62f9da3be757eee3492ee33e4d4fb113bc1d6252d943"

S = "${WORKDIR}/re-${PV}"

EXTRA_OEMAKE = "\
    'LD=${CC}' \
    'EXTRA_LFLAGS=${TARGET_LDFLAGS}' \
    'OS=linux' \
    'ARCH=${TARGET_ARCH}' \
    'SYSROOT=${STAGING_EXECPREFIXDIR}' \
    'EXTRA_CFLAGS=${CFLAGS}' \
    'DESTDIR=${D}' \
"

do_configure() {
    oe_runmake info
}

do_compile() {
    unset CFLAGS
    oe_runmake all
}

do_install() {
    oe_runmake install
}

FILES_${PN} = "${libdir}/*.so"
FILES_${PN}-dev = "${datadir}/re/re.mk ${includedir}/re/* ${libdir}/pkgconfig/*.pc"
FILES_${PN}-staticdev = "${libdir}/*.a"
