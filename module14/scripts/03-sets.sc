
// immutable Sets


val vowels = Set('a', 'e', 'i', 'o', 'u')

vowels.contains('a')
vowels.contains('t')

vowels('a')
vowels('t')

vowels + 'y'
vowels + 'e'

vowels

val commonLetters = Set('e','t','a','o','i','n','s','r','h')

commonLetters intersect vowels
commonLetters diff vowels
vowels diff commonLetters
commonLetters union vowels


// set can be used like a predicate

"hello to me to you".count(vowels)

"hello to me to you".map(vowels)

"hello to me to you".filter(vowels)

"hello to me to you".head

import scala.collection.immutable

// not ordered, but can be sorted
var vowelsImm = immutable.TreeSet('u', 'o', 'i', 'e', 'a')

vowelsImm += 'k'
vowelsImm


// mutable

import scala.collection.mutable

val vowelsMut = mutable.Set('a', 'e', 'i', 'o', 'u')

vowelsMut += 'y'
vowelsMut('y')

vowelsMut -= 'y'
vowelsMut('y')

vowelsMut('y') = true
vowelsMut('y')

vowelsMut('y') = false
vowelsMut('y')


