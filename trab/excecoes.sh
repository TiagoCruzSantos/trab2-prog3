ant compile

echo
echo "--- ERRO 1: ---"
ant run_e11 | grep 'Error 1'
ant run_e12 | grep 'Error 1'
ant run_e13 | grep 'Error 1'
ant run_e14 | grep 'Error 1'
ant run_e15 | grep 'Error 1'
echo

echo "--- ERRO 2: ---"
ant run_e21 | grep 'Error 2'
ant run_e22 | grep 'Error 2'
ant run_e23 | grep 'Error 2'
ant run_e24 | grep 'Error 2'
ant run_e25 | grep 'Error 2'
ant run_e26 | grep 'Error 2'
ant run_e27 | grep 'Error 2'
ant run_e28 | grep 'Error 2'
ant run_e29 | grep 'Error 2'
ant run_e210 | grep 'Error 2'
ant run_e211 | grep 'Error 2'
ant run_e212 | grep 'Error 2'
ant run_e213 | grep 'Error 2'
ant run_e214 | grep 'Error 2'
ant run_e215 | grep 'Error 2'
ant run_e216 | grep 'Error 2'
ant run_e217 | grep 'Error 2'
ant run_e218 | grep 'Error 2'
ant run_e219 | grep 'Error 2'
ant run_e220 | grep 'Error 2'
echo

echo "--- ERRO 3: ---"
ant run_e32 | grep 'Error 3'
ant run_e33 | grep 'Error 3'
ant run_e34 | grep 'Error 3'
ant run_e35 | grep 'Error 3'
ant run_e36 | grep 'Error 3'
ant run_e37 | grep 'Error 3'
echo
