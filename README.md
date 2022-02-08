# Open-Source Chip Design

This is a special course running at DTU in spring semester 2022.

## Commands to get Started

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