// Generated from C:/Users/Lycantropus/Desktop/COMP/proj/src\XMLParser.g4 by ANTLR 4.5
package gen;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XMLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XMLParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(@NotNull XMLParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#xmlItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlItems(@NotNull XMLParser.XmlItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#attributeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeName(@NotNull XMLParser.AttributeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(@NotNull XMLParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#attributeValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeValue(@NotNull XMLParser.AttributeValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#airportElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAirportElement(@NotNull XMLParser.AirportElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#airportElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAirportElements(@NotNull XMLParser.AirportElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(@NotNull XMLParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#servicesElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServicesElement(@NotNull XMLParser.ServicesElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#servicesElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServicesElements(@NotNull XMLParser.ServicesElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#fuelElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuelElement(@NotNull XMLParser.FuelElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#towerElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTowerElement(@NotNull XMLParser.TowerElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#runwayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunwayElement(@NotNull XMLParser.RunwayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#runwayElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunwayElements(@NotNull XMLParser.RunwayElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#markingsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkingsElement(@NotNull XMLParser.MarkingsElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#lightsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLightsElement(@NotNull XMLParser.LightsElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#offsetThresholdElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffsetThresholdElement(@NotNull XMLParser.OffsetThresholdElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#approachLightsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApproachLightsElement(@NotNull XMLParser.ApproachLightsElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#vasiElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVasiElement(@NotNull XMLParser.VasiElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#ilsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIlsElement(@NotNull XMLParser.IlsElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#ilsElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIlsElements(@NotNull XMLParser.IlsElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#glideSlopeElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlideSlopeElement(@NotNull XMLParser.GlideSlopeElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#dmeElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmeElement(@NotNull XMLParser.DmeElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#startElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartElement(@NotNull XMLParser.StartElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#runwayAliasElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#waypointElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaypointElement(@NotNull XMLParser.WaypointElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#routeElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteElement(@NotNull XMLParser.RouteElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#routeElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteElements(@NotNull XMLParser.RouteElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#nextElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNextElement(@NotNull XMLParser.NextElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#previousElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreviousElement(@NotNull XMLParser.PreviousElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#helipadElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelipadElement(@NotNull XMLParser.HelipadElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#jetwayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJetwayElement(@NotNull XMLParser.JetwayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#sceneryObjectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSceneryObjectElement(@NotNull XMLParser.SceneryObjectElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#sceneryObjectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSceneryObjectElements(@NotNull XMLParser.SceneryObjectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#libraryObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibraryObject(@NotNull XMLParser.LibraryObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#taxiwayPointElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxiwayPointElement(@NotNull XMLParser.TaxiwayPointElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#taxiwayParkingElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#taxiNameElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxiNameElement(@NotNull XMLParser.TaxiNameElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#taxiwayPathElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#taxiwaySignElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxiwaySignElement(@NotNull XMLParser.TaxiwaySignElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XMLParser#startpoint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartpoint(@NotNull XMLParser.StartpointContext ctx);
}