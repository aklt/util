# Autogenerate README.md

printf "
;; [![build status](https://travis-ci.org/rasmuserik/solsort-new.svg?branch=master)](https://travis-ci.org/rasmuserik/solsort-new)
;; [![cljs dependency status](http://jarkeeper.com/rasmuserik/solsort-new/status.png)](http://jarkeeper.com/rasmuserik/solsort-new)
;; [![node.js dependency status](https://david-dm.org/rasmuserik/solsort-new.svg)](https://david-dm.org/rasmuserik/solsort-new)
;;
;; \`autogenerated README.md from literate source code, do not edit directly\`
;;
" |
cat - src/*/core.cljs |
tr "\n" "\r" |
sed -e "s/\(\r;;[^\r]*\r\)\([^;]\)/\1\r\2/g" |
tr "\r" "\n" |
sed -e "s/^/    /" |
sed -e "s/^    ;; \\?//" \
  > README.md
