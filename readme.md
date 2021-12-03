# Keep your code debuggable
This is the source repository for my talk at the TwoDigits winter summit about working in restricted environments.

The example shows the following:
- Usage of Spring profiles to switch injection of interface implementations on the fly
- Configuration of a Spring Boot application according to the active profile
- FileReaderWriter as a workaround to get database results locally if they cannot be reached via the network