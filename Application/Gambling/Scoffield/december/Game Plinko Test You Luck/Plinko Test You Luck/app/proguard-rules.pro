#WebChromeClient
-keep class * extends android.webkit.WebChromeClient { *; }

#Box2d
-keepclassmembers class com.badlogic.gdx.physics.box2d.World {
   boolean contactFilter(long, long);
   void    beginContact(long);
   void    endContact(long);
   void    preSolve(long, long);
   void    postSolve(long, long);
   boolean reportFixture(long);
   float   reportRayFixture(long, float, float, float, float, float);
}
-keepattributes Signature
-dontwarn javax.annotation.Nullable

#-keepclassmembers class com.yourcompany.models.** { *; }
