#!/bin/bash
airmon-ng start wlan0
airodump-ng -w myOutput --output-format csv wlan0 --write-interval 30