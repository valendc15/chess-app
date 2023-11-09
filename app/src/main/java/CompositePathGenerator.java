import org.austral.game.commons.Movement;
import org.austral.game.commons.PathGenerator;
import org.austral.game.commons.PathResult;
import org.austral.game.commons.Position;

public class CompositePathGenerator implements PathGenerator {

    private PathGenerator[] pathGenerators;

    public CompositePathGenerator(PathGenerator[] pathGenerators) {
        this.pathGenerators = pathGenerators;
    }

    public PathResult generatePath(Movement movement) {
        Position[] combinedPath = new Position[0];

        for (PathGenerator pathGenerator : pathGenerators) {
            PathResult pathResult = pathGenerator.generatePath(movement);
            if (pathResult.getPath() != null) {
                combinedPath = concatenateArrays(combinedPath, pathResult.getPath());
            }
        }

        return new PathResult(combinedPath);
    }

    private Position[] concatenateArrays(Position[] arr1, Position[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        Position[] result = new Position[len1 + len2];
        System.arraycopy(arr1, 0, result, 0, len1);
        System.arraycopy(arr2, 0, result, len1, len2);
        return result;
    }
}
