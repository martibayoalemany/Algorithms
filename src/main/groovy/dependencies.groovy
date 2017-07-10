
// https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable
@Grapes(
    @Grab(group='nz.ac.waikato.cms.weka', module='weka-stable', version='3.8.1')
)
public class DependenciesCheck {
    public static String check () {
        return ""
    }
}

import com.google.common.collect.HashBiMap
@Grab(group='com.google.code.google-collections', module='google-collect', version='snapshot-20080530')
def getFruit() { [grape:'purple', lemon:'yellow', orange:'orange'] as HashBiMap }
assert fruit.lemon == 'yellow'
assert fruit.inverse().yellow == 'lemo'