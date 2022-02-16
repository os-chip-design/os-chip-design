# Open-Source Chip Design

This is a special course running at DTU in spring semester 2022.

## Commands to get Started

Make sure that Python3 is default and in a later version (3.6 or 3.8 for Caravel)

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

## Notes

https://docs.google.com/document/d/1B4SUZ5X-Ca8k_reIMNJzn70KjauhOAQU3Uh72D38uhM/edit?usp=sharing

## Links



### Machine setup for Caravel

Caravel is the environment that we need to use for the Skywater chip.

https://github.com/efabless/caravel

https://github.com/efabless/caravel_user_project/blob/main/docs/source/roundtrip.rst

https://github.com/efabless/caravel_board


#### Additional Installation Notes

 * ```sudo apt install tcsh csh tcl-dev tk-dev libcairo2-dev```
 * Install magic from source
 * Do NOT set the ```PDK_ROOT``` and ```OPENLANE_ROOT``` to a folder *within* Caravel
 * ```sudo chmod 666 /var/run/docker.sock``` if error
 * When ```make pdk``` fails, remove and try again


## Random Tasks

 * Patmos has a large memory footprint. A naked compilation configuration would be good (similar to bootable)

