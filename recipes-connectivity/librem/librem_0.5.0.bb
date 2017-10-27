SUMMARY = "librem"
DESCRIPTION = "Audio and video processing media library."
HOMEPAGE = "http://creytiv.com/rem.html"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://docs/COPYING;md5=720bf8754d76ae6c201306a8e1a36a6b"
DEPENDS = "libre"
RDEPENDS_${PN} = "libre"

SRC_URI = "https://github.com/creytiv/rem/archive/v${PV}.tar.gz;downloadfilename=${PN}_${PV}.tar.gz"

SRC_URI[md5sum] = "1863035dce4ac703a04741414368369c"
SRC_URI[sha256sum] = "310f19ef25b683a418859896a7b214e16394ae753dc69b7ad3cb241eaadd7afa"

S = "${WORKDIR}/rem-${PV}"

EXTRA_OEMAKE = "\
    'LIBRE_MK=${STAGING_DATADIR}/re/re.mk' \
    'LIBRE_INC=${STAGING_INCDIR}/re' \
    'LIBRE_SO={STAGING_LIBDIR}' \
    'LD=${CC}' \
    'EXTRA_LFLAGS=${TARGET_LDFLAGS}' \
    'OS=linux' \
    'ARCH=${TARGET_ARCH}' \
    'SYSROOT=${STAGING_EXECPREFIXDIR}' \
    'EXTRA_CFLAGS=${CFLAGS} -I${STAGING_INCDIR}' \
    'DESTDIR=${D}' \
"

do_compile() {
    unset CFLAGS
    oe_runmake
}

do_install() {
    oe_runmake install
}

FILES_${PN} = "${libdir}/*.so"
FILES_${PN}-dev = "${includedir}/rem/* ${libdir}/pkgconfig/*.pc"
FILES_${PN}-staticdev = "${libdir}/*.a"
