ant compile

echo
echo "--- ERRO 1: ---"
ant run_e11 | grep 'Erro de I/O'
ant run_e12 | grep 'Erro de I/O'
ant run_e13 | grep 'Erro de I/O'
ant run_e14 | grep 'Erro de I/O'
ant run_e15 | grep 'Erro de I/O'
echo

echo "--- ERRO 2: ---"
ant run_e21 | grep 'Erro de formatação'
ant run_e22 | grep 'Erro de formatação'
ant run_e23 | grep 'Erro de formatação'
ant run_e24 | grep 'Erro de formatação'
ant run_e25 | grep 'Erro de formatação'
ant run_e26 | grep 'Erro de formatação'
ant run_e27 | grep 'Erro de formatação'
ant run_e28 | grep 'Erro de formatação'
ant run_e29 | grep 'Erro de formatação'
ant run_e210 | grep 'Erro de formatação'
ant run_e211 | grep 'Erro de formatação'
ant run_e212 | grep 'Erro de formatação'
ant run_e213 | grep 'Erro de formatação'
ant run_e214 | grep 'Erro de formatação'
ant run_e215 | grep 'Erro de formatação'
ant run_e216 | grep 'Erro de formatação'
ant run_e217 | grep 'Erro de formatação'
ant run_e218 | grep 'Erro de formatação'
ant run_e219 | grep 'Erro de formatação'
ant run_e220 | grep 'Erro de formatação'
echo

# echo "--- ERRO 3: ---"
# ant run_e32 | grep 'Error 3'
# ant run_e33 | grep 'Error 3'
# ant run_e34 | grep 'Error 3'
# ant run_e35 | grep 'Error 3'
# ant run_e36 | grep 'Error 3'
# ant run_e37 | grep 'Error 3'
# echo
