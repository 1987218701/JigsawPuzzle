@echo off
Setlocal Enabledelayedexpansion
set "str= "
for /f "delims=" %%i in ('dir /b *.*') do (
set "var=%%i" & ren "%%i" "!var:%str%=!")



ren a(1).jpg 1.jpg
ren a(2).jpg 2.jpg
ren a(3).jpg 3.jpg
ren a(4).jpg 4.jpg
ren a(5).jpg 5.jpg
ren a(6).jpg 6.jpg
ren a(7).jpg 7.jpg
ren a(8).jpg 8.jpg
ren a(9).jpg 9.jpg
ren a(10).jpg 10.jpg
ren a(11).jpg 11.jpg
ren a(12).jpg 12.jpg
ren a(13).jpg 13.jpg
ren a(14).jpg 14.jpg
ren a(15).jpg 15.jpg
ren a(16).jpg 16.jpg
ren a(17).jpg 17.jpg
ren a(18).jpg 18.jpg
ren a(19).jpg 19.jpg
ren a(20).jpg 20.jpg
