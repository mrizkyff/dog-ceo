# Dog CEO API

[![Code Coverage](https://codecov.io/gh/ElliottLandsborough/dog-ceo-api/branch/master/graph/badge.svg)](https://codecov.io/gh/ElliottLandsborough/dog-ceo-api)
[![CircleCI](https://circleci.com/gh/ElliottLandsborough/dog-ceo-api.svg?style=svg)](https://circleci.com/gh/ElliottLandsborough/dog-ceo-api)
[![Code Style](https://github.styleci.io/repos/97956282/shield?style=flat&branch=master)](https://github.styleci.io/repos/97956282)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/28e7bd35f2fe4d42a19aec5f705c5024)](https://www.codacy.com/app/ElliottLandsborough/dog-ceo-api?utm_source=github.com&utm_medium=referral&utm_content=ElliottLandsborough/dog-ceo-api&utm_campaign=Badge_Grade)

## Info

- To add your own images submit a pull request to https://github.com/jigsawpieces/dog-api-images
- API requests are cached from lambda https://github.com/ElliottLandsborough/dog-ceo-api-golang

## Examples

- Vanilla JS: https://codepen.io/elliottlan/pen/MNEWNx
- Jquery: https://codepen.io/elliottlan/pen/KOXKLG
- Flutter: https://github.com/LIVELUCKY/dogs
- Node.js: https://github.com/mrbrunelli/dog-time-decorator

## Stats

![Screenshot of statistics page](https://github.com/ElliottLandsborough/dog-ceo-api/blob/master/stats.png?raw=true)

## Dependencies

- php 8.1+
- Symfony 6
- modules
- composer
- run './bin/phpunit' for unit tests (composer dump-env test)

```bash
$ composer check-platform-reqs
Checking platform requirements for packages in the vendor dir
composer-plugin-api  2.3.0     success                                       
composer-runtime-api 2.2.2     success                                       
ext-ctype            8.1.12    success                                       
ext-dom              20031129  success                                       
ext-filter           8.1.12    success                                       
ext-iconv            8.1.12    success                                       
ext-json             8.1.12    success                                       
ext-libxml           8.1.12    success                                       
ext-mbstring         *         success provided by symfony/polyfill-mbstring 
ext-phar             8.1.12    success                                       
ext-tokenizer        8.1.12    success                                       
ext-xml              8.1.12    success                                       
ext-xmlwriter        8.1.12    success                                       
ext-yaml             2.2.2     success                                       
php                  8.1.12    success
```

## Setup

- Clone repo
- composer install
- cd public
- php -S 127.0.0.1:6969

## .env.local

```
DOG_CEO_CACHE_KEY="something-really-secure-lol"
DOG_CEO_LAMBDA_URI=https://example.execute-api.us-east-1.amazonaws.com/dev/
```

## Cache clear:

```
$ curl -X GET http://127.0.0.1:8000/cache-clear -H 'auth-key: something-really-secure-lol'
```

## Endpoints

List all breed names including sub breeds.
#### https://dog.ceo/api/breeds/list/all

Get random breed including any sub breeds.
#### https://dog.ceo/api/breeds/list/all/random

Get 10 random breeds including any sub breeds.
#### https://dog.ceo/api/breeds/list/all/random/10

List all master breed names.
#### https://dog.ceo/api/breeds/list

Get single random master breed.
#### https://dog.ceo/api/breeds/list/random

Get 10 random master breeds.
#### https://dog.ceo/api/breeds/list/random/10

List sub breeds.
#### https://dog.ceo/api/breed/{breed}/list

List random sub breed.
#### https://dog.ceo/api/breed/{breed}/list/random

List 10 random sub breeds.
#### https://dog.ceo/api/breed/{breed}/list/random/10

Get master breed info (data is incomplete, see content folder).
#### https://dog.ceo/api/breed/{breed}

Get sub breed info (data is incomplete, see content folder).
#### https://dog.ceo/api/breed/{breed}/{breed2}

Random image from any breed.
#### https://dog.ceo/api/breeds/image/random

Get 3 random images from any breed (max. 50)
#### https://dog.ceo/api/breeds/image/random/3

Get all breed images.
#### https://dog.ceo/api/breed/{breed}/images

Get random image from a breed (and all its sub-breeds).
#### https://dog.ceo/api/breed/{breed}/images/random

Get 4 random images from a breed (and all its sub-breeds).
#### https://dog.ceo/api/breed/{breed}/images/random/4

Get all images from a sub breed.
#### https://dog.ceo/api/breed/{breed}/{breed2}/images

Get random image from a sub breed.
#### https://dog.ceo/api/breed/{breed}/{breed2}/images/random

Get 5 random images from a sub breed.
#### https://dog.ceo/api/breed/{breed}/{breed2}/images/random/5

## Beta/Unfinished Endpoints

These endpoints might change in the future...

### Alt tags (beta)

```
https://dog.ceo/api/breeds/image/random/alt
https://dog.ceo/api/breeds/image/random/1/alt
https://dog.ceo/api/breeds/image/random/9/alt
```

```
https://dog.ceo/api/breed/hound/images/alt
https://dog.ceo/api/breed/hound/images/random/1/alt
https://dog.ceo/api/breed/hound/images/random/9/alt
```

```
https://dog.ceo/api/breed/hound/afghan/images/alt
https://dog.ceo/api/breed/hound/afghan/images/random/alt
```

### XML Responses (beta, unfinished)

Add 'Content-Type' request header containing 'application/xml' to any endpoint.