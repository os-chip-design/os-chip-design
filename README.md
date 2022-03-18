# Open-Source Chip Design

This is a special course running at DTU in spring semester 2022.

## Meeting Notes

https://docs.google.com/document/d/1B4SUZ5X-Ca8k_reIMNJzn70KjauhOAQU3Uh72D38uhM/edit?usp=sharing

## Commands to get Started

Make sure that Python3 is default and in a later version (3.6 or 3.8 for Caravel)

Also make sure you have Docker installed: (if not, instructions for install: https://docs.docker.com/engine/install/ubuntu/)

## Links

https://symbiflow-examples.readthedocs.io/en/latest/

A reference to an SPI Flash model (implementations of many IO devices are in their own repos): https://github.com/pulp-platform/pulp/tree/master/rtl/vip/spi_flash

### Machine setup for Caravel

Caravel is the environment that we need to use for the Skywater chip.

https://github.com/efabless/caravel

https://github.com/efabless/caravel_board

Instructions for setting up caravel user project:

https://github.com/efabless/caravel_user_project/blob/main/docs/source/quickstart.rst

#### Additional Installation Notes

 * ```sudo apt install tcsh csh tcl-dev tk-dev libcairo2-dev```
 * Install magic from source (https://github.com/RTimothyEdwards/magic)
 * Do NOT set the ```PDK_ROOT``` and ```OPENLANE_ROOT``` to a folder *within* Caravel
 * If docker.sock permission denied use:```sudo usermod -a -G docker USER # Replace USER with your user``` (restart is required) 
 * When ```make pdk``` fails, remove and try again
 * In step 1, there can be merge issues with git if the repo is created with a README. (A README can be created later)
 * If ```make precheck``` fails with *GOLDEN_CARAVEL envrionment variable is not set. Please set it to point to absolute path to the golden caravel*, change ```CARAVEL_ROOT``` to ```GOLDEN_CARAVEL``` in *mpw_precheck.py*

For the submission to the MPW run we need to use the branch, see https://docs.google.com/document/d/1Y7LuP_0dJ_vmD8G_Twc6qc97fj7aW5pRV5nAjN2oOUk/edit#
Latest version is ```mpw-5c```

### Setup for OpenROAD

Instructions for setting up OpenROAD are found here:

https://github.com/The-OpenROAD-Project/OpenROAD#openroad

**OBS!** If your distro isn't Ubuntu 20.04 or Centos7, then instructions for installing dependencies are found here:

https://github.com/chiselverify/os-chip-design/blob/main/OpenROAD_Dependencies.md

### Setup fo OpenLane

We are not directly using it, but as part of Caravel. So thi might be a bit outdated.

```
git clone https://github.com/The-OpenROAD-Project/OpenLane.git
cd OpenLane/
make pull-openlane
make pdk
make test
```

### Build Something in Openlane

```
make mount
flow.tcl -design designs/picorv32a
```

Exit the docker and

```
sudo apt install klayout
cd designs/picorv32a/runs/RUN.../results
cd routing
klayout *.def
```

### Architecture

![Alt text](patmos.drawio.svg?raw=true)

## Random Tasks

 * Patmos has a large memory footprint. A naked compilation configuration would be good (similar to bootable)
 * Initialization of the PC has strange usage of !reset and input pins, because of Chisel 3 changes for Verilator. We also want to set the PC. Maybe we fine one common (better) solution.

## Ideas for Additional Cricuits

 * sigma-delta ADC/DAC
 * asynchronous circuit in Chisel as Patmos peripheral

