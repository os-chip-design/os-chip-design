## OpenROAD Dependencies 
(if the *DependencyInstaller.sh* doesn't support your linux distro)

But needed if on Ubuntu 20.04 (and some other distros)

#### Programs that can be installed by apt
```
sudo apt -y install \
  cmake \ 
  tzdata \
  automake \
  autotools-dev \
  bison \
  flex \
  clang \
  g++ \
  gcc \
  git \
  lcov \
  libpcre3-dev \
  python3-dev \
  libreadline-dev \
  tcl-dev \
  tcllib \
  wget \
  zlib1g-dev \
  libboost-all-dev \
  libspdlog-dev \
  libeigen3-dev 
```
### Programs that must be installed from source
#### Swig
```
wget https://github.com/swig/swig/archive/rel-4.0.1.tar.gz
md5sum -c <(echo "ef6a6d1dec755d867e7f5e860dc961f7  rel-4.0.1.tar.gz") || exit 1 # This is just a check for the correct package version
tar xfz rel-4.0.1.tar.gz
cd swig-rel-4.0.1
./autogen.sh
./configure --prefix=/usr
make -j $(nproc)
sudo make -j $(nproc) install
```

#### LEMON
```
wget http://lemon.cs.elte.hu/pub/sources/lemon-1.3.1.tar.gz
md5sum -c <(echo "e89f887559113b68657eca67cf3329b5  lemon-1.3.1.tar.gz") || exit 1 # This is just a check for the correct package version
tar -xf lemon-1.3.1.tar.gz
cd lemon-1.3.1
cmake -B build .
sudo cmake --build build -j $(nproc) --target install
```

### Additional notes:
  * to install openroad (after ```make```) use: ```sudo make install```
