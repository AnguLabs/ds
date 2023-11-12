package angu.labs.ds.tokens.shapes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

//https://base.uber.com/6d2425e9f/p/652959-corner-radius/b/224ec9

public object DsShapes{
    public val xSmall: CornerBasedShape = RoundedCornerShape(2.dp)
    public val small: CornerBasedShape = RoundedCornerShape(4.dp)
    public val medium: CornerBasedShape = RoundedCornerShape(8.dp)
    public val large: CornerBasedShape = RoundedCornerShape(12.dp)
    public val xLarge: CornerBasedShape = RoundedCornerShape(16.dp)
    public val circle: CornerBasedShape = CircleShape
}