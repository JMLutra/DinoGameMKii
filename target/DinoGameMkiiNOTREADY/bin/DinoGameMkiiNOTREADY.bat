@echo off
set JLINK_VM_OPTIONS=
set DIR=%~dp0
"%DIR%\java" %JLINK_VM_OPTIONS% -m de.jmlutra.DinoGameMKii/de.jmlutra.dinogamemkii.DinoGameApp %*
