# Signal Android Websockets-Only

Signal is a messaging app for simple private communication with friends.

Signal uses your phone's data connection (WiFi/3G/4G) to communicate securely, optionally supports plain SMS/MMS to function as a unified messenger, and can also encrypt the stored messages on your phone.

#WebSocket Support#

This branch adds rudimentary WebSocket-ONLY support to Signal Private Messenger.

## THIS CODE IS ALPHA QUALITY AND HAS KNOWN BUGS. PLEASE BE AWARE AND USE AT YOUR OWN RISK.

In specific, Signal ZRTP encrypted calls do not work at this time. That code is commented out of this websocket-only version.

## Building the Websockets Version

In order to build this fork, a modified version of the libtextsecure library is needed. You can find that [here](https://github.com/JavaJens/libtextsecure-java/tree/fix/maven_local).

Once the code for the libtextsecure library has been checked out, you will want to run ````./gradlew tasks installArchives```` to install the alternate, websockets version in local maven cache on your computer.

See further, more in-depth building instructions to compile this application [here](https://github.com/JavaJens/TextSecure/wiki/Building-the-Websockets-reborn-fork).

## Contributing Bug reports
We use GitHub for bug tracking. Please search the existing issues for your bug and create a new one if the issue is not yet tracked!

https://github.com/WhisperSystems/Signal-Android/issues

## Contributing Translations
Interested in helping to translate Signal? Contribute here:

https://www.transifex.com/projects/p/signal-android/

## Contributing Code
General instructions on how to setup your development environment and build vanilla Signal can be found in  [BUILDING.md](https://github.com/WhisperSystems/Signal-Android/blob/master/BUILDING.md).

If you're new to the Signal codebase, we recommend going through our issues and picking out a simple bug to fix (check the "easy" label in our issues) in order to get yourself familiar.

For larger changes and feature ideas, we ask that you propose it on the mailing list for a high-level discussion before implementation.

This repository is set up with [BitHub](https://whispersystems.org/blog/bithub/), so you can make money for committing to Signal. The current BitHub price for an accepted pull request is:

[![Current BitHub Price](https://bithub.herokuapp.com/v1/status/payment/commit/)](https://whispersystems.org/blog/bithub/)

## Contributing Ideas
Have something you want to say about Open Whisper Systems projects or want to be part of the conversation? Get involved in the mailing list!

whispersystems@lists.riseup.net

https://lists.riseup.net/www/info/whispersystems

## Contributing Funds
[![Donate](https://cloud.githubusercontent.com/assets/3121306/11278543/d46e03d0-8eeb-11e5-9691-0da1bf643192.png)](https://www.coinbase.com/checkouts/51dac699e660a4d773216b5ad94d6a0b)

You can add funds to BitHub to directly help further development efforts.

Help
====
## Support
For troubleshooting and questions, please visit our support center!

http://support.whispersystems.org/

## Documentation
Looking for documentation? Check out the wiki!

https://github.com/WhisperSystems/Signal-Android/wiki

# Legal things
## Cryptography Notice

This distribution includes cryptographic software. The country in which you currently reside may have restrictions on the import, possession, use, and/or re-export to another country, of encryption software.
BEFORE using any encryption software, please check your country's laws, regulations and policies concerning the import, possession, or use, and re-export of encryption software, to see if this is permitted.
See <http://www.wassenaar.org/> for more information.

The U.S. Government Department of Commerce, Bureau of Industry and Security (BIS), has classified this software as Export Commodity Control Number (ECCN) 5D002.C.1, which includes information security software using or performing cryptographic functions with asymmetric algorithms.
The form and manner of this distribution makes it eligible for export under the License Exception ENC Technology Software Unrestricted (TSU) exception (see the BIS Export Administration Regulations, Section 740.13) for both object code and source code.

## License

Copyright 2011 Whisper Systems

Copyright 2013-2015 Open Whisper Systems

Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
