package arboles;

/**
 * Enumeration that defines different formatting strategies for representing
 * binary search tree nodes as strings.
 *
 * This enum provides three formatting options: - BASIC: displays only the
 * element value - HEIGHT: displays the element value and node height -
 * HEIGHT_AND_BALANCE_FACTOR: displays the element value, node height, and
 * balance factor
 *
 * Each format wraps the node information in curly braces and adds a trailing
 * space. Null nodes are represented as empty curly braces.
 *
 */
public enum NodeFormat {

    /**
     * Basic formatting that displays only the element value.
     *
     * Format: {element} Example: {5} Null nodes: {}
     */
    BASIC {
        /**
         * Formats a node showing only its element value.
         *
         * @param <T>  the type of element stored in the node
         * @param node the node to format
         * @return formatted string containing only the element, or empty braces for
         *         null
         */
        @Override
        <T extends Comparable<T>> String format(TreeNode<T> node) {
            return node == null ? formatNullNode() : formatNodeFromData(node.element);
        }
    },

    /**
     * Formatting that displays the element value and node height.
     *
     * Format: {element|height} Example: {5|2} Null nodes: {}
     */
    HEIGHT {
        /**
         * Formats a node showing its element value and height.
         *
         * @param <T>  the type of element stored in the node
         * @param node the node to format
         * @return formatted string containing element and height, or empty braces for
         *         null
         */
        @Override
        <T extends Comparable<T>> String format(TreeNode<T> node) {
            return node == null ? formatNullNode() : formatNodeFromData(node.element, node.getHeight());
        }
    },

    /**
     * Formatting that displays the element value, node height, and balance factor.
     *
     * Format: {element|height|balanceFactor} Example: {5|2|1} Null nodes: {}
     */
    HEIGHT_AND_BALANCE_FACTOR {
        /**
         * Formats a node showing its element value, height, and balance factor.
         *
         * @param <T>  the type of element stored in the node
         * @param node the node to format
         * @return formatted string containing element, height, and balance factor, or
         *         empty braces for null
         */
        @Override
        <T extends Comparable<T>> String format(TreeNode<T> node) {
            return node == null ? formatNullNode()
                    : formatNodeFromData(node.element, node.getHeight(), node.getBalanceFactor());
        }
    };

    /**
     * Formats a node according to the specific formatting strategy.
     *
     * This abstract method must be implemented by each enum constant to define its
     * particular formatting behavior.
     *
     * @param <T>  the type of element stored in the node
     * @param node the node to format
     * @return formatted string representation of the node
     */
    abstract <T extends Comparable<T>> String format(TreeNode<T> node);

    /** Opening delimiter for node representation. */
    private static final String NODE_OPEN = "{";

    /** Closing delimiter for node representation. */
    private static final String NODE_CLOSE = "}";

    /** Space character added after each formatted node. */
    private static final String BLANK_SPACE = " ";

    /**
     * Separator used between different node properties within the formatted string.
     */
    private static final String INNER_SEPARATOR = "|";

    /** Representation for null nodes (empty string between braces). */
    private static final String NULL_NODE = "";

    /**
     * Wraps the given node content with opening and closing braces and adds a
     * trailing space.
     *
     * This method encapsulates the content within the standard node delimiters used
     * by all formatting strategies.
     *
     * @param nodeContent the content to wrap
     * @return the wrapped content in the format {content} with trailing space
     */
    private String wrapNode(String nodeContent) {
        return NODE_OPEN + nodeContent + NODE_CLOSE + BLANK_SPACE;
    }

    /**
     * Formats a null node as empty braces with a trailing space.
     *
     * @param <T> the type of element that would be stored in the node
     * @return formatted string representing a null node: {}
     */
    <T extends Comparable<T>> String formatNullNode() {
        return wrapNode(NULL_NODE);
    }

    /**
     * Formats a node showing only its element value.
     *
     * Used by the BASIC format strategy.
     *
     * @param <T>     the type of element stored in the node
     * @param element the element to format
     * @return formatted string in the format {element}
     */
    <T extends Comparable<T>> String formatNodeFromData(T element) {
        return wrapNode(element.toString());
    }

    /**
     * Formats a node showing its element value and height.
     *
     * Used by the HEIGHT format strategy. The element and height are separated by
     * the pipe character.
     *
     * @param <T>     the type of element stored in the node
     * @param element the element to format
     * @param height  the height of the node
     * @return formatted string in the format {element|height}
     */
    <T extends Comparable<T>> String formatNodeFromData(T element, int height) {
        String nodeContent = element + INNER_SEPARATOR + height;
        return wrapNode(nodeContent);
    }

    /**
     * Formats a node showing its element value, height, and balance factor.
     *
     * Used by the HEIGHT_AND_BALANCE_FACTOR format strategy. The values are
     * separated by pipe characters.
     *
     * @param <T>           the type of element stored in the node
     * @param element       the element to format
     * @param height        the height of the node
     * @param balanceFactor the balance factor of the node
     * @return formatted string in the format {element|height|balanceFactor}
     */
    <T extends Comparable<T>> String formatNodeFromData(T element, int height, int balanceFactor) {
        String nodeContent = element + INNER_SEPARATOR + height + INNER_SEPARATOR + balanceFactor;
        return wrapNode(nodeContent);
    }

}