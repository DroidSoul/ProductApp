# *ProductApp*

**ProductApp** shows products at Walmart. The app utilizes the Walmart test API to display images and basic information about these products to users.


## User Stories

The following functionality is completed:

* [X] First screen should contain a List of all the products returned by the service call.
  ***used recyclerview, Async HTTP, Picasso
* [X] The list should support Lazy Loading. When scrolled to the bottom of the list, start lazy loading next page of products and append it to the list.
   ***used pagination, enlesscrolling
* [X] When a product is clicked, it should go to the second screen.
   ***used intent to go to the 2nd screen together with information of product lists and position. 
* [X] Second screen should display details of the product.
   ***name, product image, price, rating, reviewcount, in stock info, description
* [X] We should be able to swipe to next/previous items on this screen.
   ***Tried two approaches here:
   1, detailactivity, used tablayout/viewpager with three fragments so that user can swip to previous/next.
   2, (preferred)detailactivity2, used another horizontal recyclerview with each item occupying whole screen. user can swip to previous/next products and more.
* [X] Handling orientation changes efficiently will be a plus.
   *** orientation changes destroy activity, thus it will be beneficial to retain recyclerview position by saving/restoring layoutmanager instancestate. detailactivity gets list/position from mainactivity, so no problem there.
* [X] Animation
    ***Added Ripple effect for item selection and shared elements transition animation. Lots of things can be done here.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

walkthrough : https://i.imgur.com/GUAsAC8.gif (please click this one if content length exceeded)

<img src='https://i.imgur.com/GUAsAC8.gif' title='Video Walkthrough' width='806' alt='Video Walkthrough' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
1, spent a little time on page from zero and it should be from one as mentioned.
2, took some time to design how to swipe to previous/next. Tried two solutions.
3, Took some time to figure out how to save recycleview position during orientation change. Neeed to save layoutmanager state or postion directly(onSaveInstantState and onRecoverInstantState), also need to apply it after data ready (after http call and data fetched), not just after the http call(which is async, can be later), otherwise it will always go back to position 0.


## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [2018] [Mark Weixiao Huang]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
