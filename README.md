# SnappyCompressor
<b>Usage:</b> java -jar spn.jar keys [enc] input<br><br>
keys:<br>
<ul>
<li>x - uncompress</li>
<li>c - compress</li>
<li>f - use file (instead of stdin)</li>
</ul><br>
Input is one of:<br>
<ul>
<li>path to file (if 'f' key specified)</li>
<li>raw input string or pipe (one can specify 'enc' param for string encoding)</li>
</ul><br>
<b>Simple test:</b><br>
<code>java -jar snp.jar c qwerty12345 | java -jar snp.jar x</code>
<br><br>
<b>Compile:</b><br>
<ul>
<li>setup <a href="https://maven.apache.org/">Maven</a></li>
<li>run 'mvn package'</li>
<li>use target/snp-1.0-SNAPSHOT.jar</li>
</ul>
