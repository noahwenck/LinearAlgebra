# Linear Algebra in SpringBoot
This is a learning excercise for me to further my understanding of SpringBoot and Linear Algebra.
</br>

----
The gradle plugin is currently having issues as it runs an older (and not my custom) ruleset for pmd and throws a bunch of deprecated violations. Please run pmdMain/pmdTest in build.gradle to see all the lovely pmd violations this code throws.

----
##### To build with Gradle
```
./gradlew build
```

##### To run PMD
```
./gradlew check
```
Or run pmdMain or pmdTest from build.gradle

----
</br>
There is not much right now, but I will update this README in the future
once I've gotten a good start and have something tangible
(instead of just being able to test code using unit tests).

### todo
1. Develop Frontend (Function over beauty)
2. Implement Row Reduction after matrix setup (rethink how to implement)
3. Configure Gradle
4. PMD? cant believe I actually put that on a todo list to add to this
5. Row reduction is the focus, but consider other topics 
(bases, transforms, independence, orthogonality, eigenvectors/values?)
