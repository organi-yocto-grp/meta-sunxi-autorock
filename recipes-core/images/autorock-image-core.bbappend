
IMAGE_FSTYPES += "ubifs"

IMAGE_INSTALL += "emconfig mksplash packimg sunxi-fexc"
IMAGE_INSTALL += "mtd-utils mtd-utils-ubifs"
IMAGE_INSTALL += "dashboard-update"

BAD_RECOMMENDATIONS += " \
    qtbase-fonts-ttf-vera \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
"